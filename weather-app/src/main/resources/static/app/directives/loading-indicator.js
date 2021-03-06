angular.module('weather')
    .directive("loadingIndicator", function() {
        return {
            restrict: 'E',
            template: '<loader working="true" disable-background="true" message="message" template="3"></loader>',
            link: function($scope, elem) {
                $scope.message = 'Ожидайте своей очереди...';
                $scope.$on("loading:show", function () {
                    return elem.removeClass('ng-hide');
                });
                return $scope.$on("loading:hide", function () {
                    return elem.addClass('ng-hide');
                });
    
            }
        }
});
