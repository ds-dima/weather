angular.module('weather')
    .config(['$stateProvider', function config($stateProvider) {

        $stateProvider.state("by-coordinates", {
            url: "/by-coordinates",
            controller: "SearchByCoordinatesController",
            templateUrl: "app/coordinates/searchByCoordinates.html"
        });
    }])
    .controller('SearchByCoordinatesController', function ($scope) {
        

    });
