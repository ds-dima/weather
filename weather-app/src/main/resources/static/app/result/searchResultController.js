angular.module('weather')
    .config(['$stateProvider', $stateProvider => {

        $stateProvider.state("result", {
            url: "/result",
            controller: "SearchResultController",
            templateUrl: "app/result/search-result.html"
        });
    }])
    .controller('SearchResultController', function ($scope, $state, WeatherResult) {

        $scope.weather = WeatherResult.get();
        if (!$scope.weather) {
            $state.go('search');
        }
    });
