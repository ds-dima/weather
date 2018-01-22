angular.module('weather')
    .config(['$stateProvider', $stateProvider => {

        $stateProvider.state("search", {
            url: "/search",
            controller: "SearchController",
            templateUrl: "app/search/search.html"
        });
    }])
    .controller('SearchController', function ($scope, $http) {
        $scope.searchMode = 'byCity';
        $scope.search = () => {
            let future = null;
            if ($scope.isSearchByCityMode()) {
                future = searchByCity();
            } else if ($scope.isSearchByCoordinatesMode()) {
                future = searchByCoordinates();
            }
            future.then(response => {
                $scope.weather = response.data;
                $scope.clearSearch();
            })
        };

        let searchByCity = () => {
            let params = {
                params: {cityName: $scope.city},
                headers : {'Content-Type' : 'application/json; charset=UTF-8'}
            };
            return $http.get('/api/v1.0/weather/by-city-name', params)
        };

        let searchByCoordinates= () => {
            let params = {params: {lat: $scope.latitude, lon: $scope.longitude}};
            return $http.get('/api/v1.0/weather/by-coordinates', params)
        };

        $scope.isSearchByCityMode = () => {
            return $scope.searchMode === 'byCity';
        };

        $scope.isSearchByCoordinatesMode = () =>  {
            return $scope.searchMode === 'byCoordinates';
        };

        $scope.clearSearch = () => {
            $scope.city = null;
            $scope.latitude = null;
            $scope.longitude = null;
        }

    });
