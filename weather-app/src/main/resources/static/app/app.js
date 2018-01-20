"use strict";

angular.module('weather', ['ngResource', 'ui.router'])
    .config(['$stateProvider', '$urlRouterProvider', function config($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/by-city');
    }])
    .run(['$window', '$rootScope', function config($window, $rootScope) {
        $rootScope.back = function () {
            $window.history.go(-1);
        }
    }]);

