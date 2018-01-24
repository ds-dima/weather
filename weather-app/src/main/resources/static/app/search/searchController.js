angular.module('weather')
    .config(['$stateProvider', $stateProvider => {

        $stateProvider.state("search", {
            url: "/search",
            controller: "SearchController",
            templateUrl: "app/search/search.html"
        });
    }])
    .controller('SearchController', function ($scope, $http, $state, $rootScope, growl, WeatherResult, WebSocket, UserId) {
        $scope.searchMode = 'byCity';
        $scope.search = () => {
            let onSuccess = (response) => {
                $rootScope.$broadcast("loading:hide");
                let weather = JSON.parse(response.body);
                if (weather.error) {
                    growl.error(weather.error);
                    return;
                }
                WeatherResult.set(weather);
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
            $rootScope.$broadcast("loading:show");
            WebSocket.subscribe({
                source: '/' + UserId.get() + '/weather-by-city-name',
                topic:  '/topic/' + UserId.get() + '/result',
                data: $scope.city,
                handler: onSuccess
            })
        };

        let searchByCoordinates = onSuccess => {
            $rootScope.$broadcast("loading:show");
            WebSocket.subscribe({
                source: '/' + UserId.get() + '/weather-by-coordinates',
                topic:  '/topic/' + UserId.get() + '/result',
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
