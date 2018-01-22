angular.module('weather')
    .controller('MainController', function ($scope, $state, WeatherResult) {
        $scope.$watch(WeatherResult.get, function (newVal, oldVal) {
            $scope.hasWeather = !!newVal;
        });
    });
