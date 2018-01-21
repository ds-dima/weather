angular.module('weather')
    .config(['$stateProvider', $stateProvider => {

        $stateProvider.state("by-coordinates", {
            url: "/by-coordinates",
            controller: "SearchByCoordinatesController",
            templateUrl: "app/coordinates/searchByCoordinates.html"
        });
    }])
    .controller('SearchByCoordinatesController', function ($scope, $http) {
        $scope.search = function () {
            let params = {params: {lat: $scope.latitude, lon:$scope.longitude}};
            $http.get('/api/v1.0/weather/by-coordinates', params)
                .then(data => {
                    console.log(data)
                })
        }

    });
