var notEndedUserActivity = angular.module('notEndedUserActivity', []);
notEndedUserActivity.controller('notEndedUserActivityController', function notEndedUserActivityController($scope, $http) {
    $http({
        method: 'GET',
        url: 'http://localhost:8080/reports/notEndedActivity'
    }).then(function success(response) {
            $scope.report = response.data;
        },
        function errorCallback(response) {
            console.log("error");
        }
    )
});