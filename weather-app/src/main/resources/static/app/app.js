"use strict";

angular.module('weather', ['ngResource', 'ui.router', 'ngLoader'])
    .config(['$stateProvider', '$urlRouterProvider', function config($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/search');
    }])
    .factory("runnerHttpInterceptor", function ($q, $injector) {
        let numLoadings = 0;
        let rootScope = $injector.get("$rootScope");

        return {
            'request': function (config) {
                if (config && !(config.hideLoader || config.data)) {
                    numLoadings++;
                    rootScope.$broadcast("loading:show");
                }
                return config;
            },
            'response': function (response) {
                if (numLoadings > 0 && !(--numLoadings)) {
                    rootScope.$broadcast("loading:hide");
                }
                return response;
            },

            "responseError": function (rejection) {
                if (numLoadings > 0 && !(--numLoadings)) {
                    rootScope.$broadcast("loading:hide");
                }


                if (rejection.status === 400 && angular.isArray(rejection.data)) {
                   //todo
                } else if (rejection.status === 500) {
                    //todo
                } else {
                    return $q.reject(rejection);
                }
            },

            'requestError': function (rejection) {
                if (numLoadings > 0 && !(--numLoadings)) {
                    rootScope.$broadcast("loading:hide");
                }
                return rejection
            }
        }
    })
    .config(["$httpProvider", "$locationProvider", "$qProvider", function ($httpProvider, $locationProvider, $qProvider) {
        $locationProvider.hashPrefix('');
        $qProvider.errorOnUnhandledRejections(false);
        $httpProvider.interceptors.push('runnerHttpInterceptor');
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }])
    .run(['$window', '$rootScope', function config($window, $rootScope) {
        $rootScope.back = function () {
            $window.history.go(-1);
        }
    }]);

