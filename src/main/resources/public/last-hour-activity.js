var lastHourActivity = angular.module('lastHourActivity', []);
lastHourActivity.controller('lastHourActivityController', function lastHourActivityController($scope, $http) {
    $http({
        method: 'GET',
        url: 'http://localhost:8080/reports/lastHour'
    }).then(function success(response) {
            $scope.report = response.data;
        },
        function errorCallback(response) {
            console.log("error");
        }
    )
});