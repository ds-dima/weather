angular.module('weather')
    .config(['$stateProvider', function config($stateProvider) {

        $stateProvider.state("by-city", {
            url: "/by-city",
            controller: "SearchByCityController",
            templateUrl: "app/city/searchByCity.html"
        });
    }])
    .controller('SearchByCityController', function ($scope, $http) {
        $scope.search = function () {
            let params = {params: {cityName: $scope.searchText}};
            $http.get('/api/v1.0/weather/by-city-name', params)
                .then(data => {
                    console.log(data)
                })
        }

    });
