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
               let socket = new SockJS('/weather-websocket');
               let stompClient = Stomp.over(socket);
               stompClient.connect({}, function (frame) {
                   stompClient.subscribe(config.topic, config.handler);
                   let request = angular.isObject(config.data) ?
                       JSON.stringify(config.data) : config.data;
                   stompClient.send("/app" + config.source, {}, request);
               });
           }
       } 
    })
    .factory('GUID', function () {
        let s4 = () => Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        return {
           generate: () => s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4()
       }
    })

    .factory('UserId', function (GUID) {
        let id = GUID.generate();
        return {
           get: () => id
       }
    });
