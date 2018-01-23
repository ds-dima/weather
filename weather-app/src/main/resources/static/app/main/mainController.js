angular.module('weather')
    .controller('MainController', function ($scope, $http, $state, WeatherResult) {
        $scope.$watch(WeatherResult.get, function (newVal, oldVal) {
            $scope.hasWeather = !!newVal;
        });

        $scope.logout = function () {
            $http.post('/logout')
                .then(() => {
                    window.location = '/login';
                });

        }
    });
