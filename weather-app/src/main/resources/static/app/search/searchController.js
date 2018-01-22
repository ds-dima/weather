angular.module('weather')
    .config(['$stateProvider', $stateProvider => {

        $stateProvider.state("search", {
            url: "/search",
            controller: "SearchController",
            templateUrl: "app/search/search.html"
        });
    }])
    .controller('SearchController', function ($scope, $http, $state, WeatherResult, WebSocket) {
        $scope.searchMode = 'byCity';
        $scope.search = () => {
            let onSuccess = (response) => {
                WeatherResult.set(JSON.parse(response.body));
                $state.go('result');
                $scope.clearSearch();
            };
            if ($scope.isSearchByCityMode()) {
                searchByCity(onSuccess);
            } else if ($scope.isSearchByCoordinatesMode()) {
                searchByCoordinates(onSuccess);
            }
        };

        let searchByCity = onSuccess => {
            WebSocket.subscribe({
                source: '/weather-by-city-name',
                topic: '/topic/result',
                data: $scope.city,
                handler: onSuccess
            })
        };

        let searchByCoordinates = onSuccess => {
            WebSocket.subscribe({
                source: '/weather-by-coordinates',
                topic: '/topic/result',
                data: {latitude: $scope.latitude, longitude: $scope.longitude},
                handler: onSuccess
            })
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
