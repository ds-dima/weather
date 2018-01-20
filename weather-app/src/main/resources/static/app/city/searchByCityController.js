angular.module('weather')
    .config(['$stateProvider', function config($stateProvider) {

        $stateProvider.state("by-city", {
            url: "/by-city",
            controller: "SearchByCityController",
            templateUrl: "app/city/searchByCity.html"
        });
    }])
    .controller('SearchByCityController', function ($scope) {
        

    });
