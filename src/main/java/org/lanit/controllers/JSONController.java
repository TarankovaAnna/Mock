package org.lanit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lanit.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class JSONController {
    @PostMapping(path = "json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object request(@RequestBody Request request, @RequestParam String action) throws IOException {
//      Создаем объект, который будет использоваться для преобразования объектов Java в формат JSON и обратно
        ObjectMapper objectMapper = new ObjectMapper();

        Info info;

        long startTime = System.currentTimeMillis();

//      Генерируем рандомный UUID
        UUID uuid = UUID.randomUUID();

//      Устанавливаем дату и врямя в требуемом формате
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String lastUpdate = dtFormatter.format(dt);

//      Создаем объект и устанавливаем в него необходимые значения
        Response updatedResponse = new Response();
        updatedResponse.setInfo(request.getInfo());
        updatedResponse.setLastUpdate(lastUpdate);
        updatedResponse.setUuid(String.valueOf(uuid));

//      Прописываем условия для значения action = add
        if ("add".equals(action)) {
//          Получаем объект Add из объекта request
            Add add = request.getAdd();

//          Получаем список объектов TickersItem из объекта request
            List<TickersItem> tickers = request.getInfo().getTickers();
            boolean tickerFound = false;
            for (TickersItem ticker : tickers) {
//              Проверяем, соответствует ли имя тикера имени, переданному в объекте add
                if (ticker.getTicker().equals(add.getName())) {
//                  Создаем объект AlertsItem и устанавливаем в него небходимые значения
                    AlertsItem alertsItem = new AlertsItem();
                    alertsItem.setPercent(add.getPercent());
                    alertsItem.setTimeframe(add.getTimeFrame());

//                  Добавляеи объект alertsItem в список alerts в объекте ticker
                    ticker.getAlerts().add(alertsItem);

//                  Уснатавливаем необходимые значения в объекте request
                    request.setLastUpdate(lastUpdate);
                    request.setUuid(String.valueOf(uuid));

                    tickerFound = true;
                    break;
                }
            }

//          Прописываем условия для ситуации, когда отсутствует необходимый тикер
            if (!tickerFound) {
//              Создаем новый объект TickersItem
                TickersItem newTicker = new TickersItem();

//              Установливаем значения имени тикера в объекте newTicker
                newTicker.setTicker(add.getName());

//              Создаем новый список объектов AlertsItem и устанавливаем необходимые значения
                List<AlertsItem> newAlerts = new ArrayList<>();
                AlertsItem alertsItem = new AlertsItem();
                alertsItem.setPercent(add.getPercent());
                alertsItem.setTimeframe(add.getTimeFrame());

//              Добавляем объект alertsItem в список newAlerts
                newAlerts.add(alertsItem);

//              Установливаем список newAlerts в объекте newTicker
                newTicker.setAlerts(newAlerts);

//              Добавляем объект newTicker в список tickers
                tickers.add(newTicker);

//              Уснатавливаем необходимые значения в объекте request
                request.setLastUpdate(lastUpdate);
                request.setUuid(String.valueOf(uuid));
            }

//      Прописываем условия для значения delete
        } else if ("delete".equals(action)) {
//          Получаем объект Delete из объекта request
            Delete delete = request.getDelete();

//          Получаем список объектов TickersItem из объекта request
            List<TickersItem> tickers = request.getInfo().getTickers();
            boolean removed = false;
            for (TickersItem ticker : tickers) {
//              Проверяем, соответствует ли имя тикера имени, переданному в объекте delete
                if (ticker.getTicker().equals(delete.getTickerName())) {
//                  Получаем список объектов AlertsItem из объекта ticker
                    List<AlertsItem> alerts = ticker.getAlerts();
//                  Проверяем, существует ли элемент с указанным индексом в списке alerts
                    if (alerts.size() > delete.getAlertIndex()) {
//                      Удаляем элемент с указанным индексом
                        alerts.remove(delete.getAlertIndex());

                        removed = true;
                        break;
                    } else {
//                      Отправляем ответ со статусом 404, в случае отсутствия указанного индекса
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Передан некорректный индекс");
                    }
                }
            }

            if (removed) {
//              Уснатавливаем необходимые значения в объекте request
                request.setLastUpdate(lastUpdate);
                request.setUuid(String.valueOf(uuid));
            } else {
//              Отправляем ответ со статусом 404, если передан некорректный тикер
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Передан некорректный тикер");
            }

        } else {
//          Отправляем ответ со статусом 400 и текстом запроса
            return ResponseEntity.badRequest()
                    .header("content-type", "application/json")
                    .body("{\"message\": \"Передан некорректный action - ".concat(action).concat("\"}"));
        }

//      Отправляем ответ со статусом 200, требуемыми Header`ами и телом ответа с необходимыми параметрами
        return ResponseEntity.ok()
                .header("content-type", "application/json")
                .body(objectMapper.writeValueAsString(updatedResponse));
    }
}
