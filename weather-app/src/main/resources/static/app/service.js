angular.module('weather')
    .factory('WeatherResult', function () {
        let result = null;
        return {
            get: () => {
                return result;
            },
            set: (res) => {
                result = res;
            }
        }
    })
    .factory('WebSocket', function () {
       return {
           subscribe: (config) => {
               var socket = new SockJS('/weather-websocket');
               let stompClient = Stomp.over(socket);
               stompClient.connect({}, function (frame) {
                   console.log('Connected: ' + frame);
                   stompClient.subscribe(config.topic, config.handler);
                   var request = angular.isObject(config.data) ?
                       JSON.stringify(config.data) : config.data;
                   stompClient.send("/app" + config.source, {}, request);
               });
           }
       } 
    });