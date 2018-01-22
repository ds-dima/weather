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
    });