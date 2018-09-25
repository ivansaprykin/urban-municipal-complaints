/**
 * Created by Ivan on 6/7/2015.
 */

/* Controllers */

/*
 angular.module("myApp.controllers", []).controller("Controller1", function($scope, $location, $state) {
 $scope.loadView2 = function() {
 $state.go('view2', {
 firstname: $scope.firstname,
 lastname: $scope.lastname
 });
 }
 }).controller("Controller2", function($scope, $stateParams, names, jobs) {
 $scope.firstname = $stateParams.firstname;
 $scope.lastname = $stateParams.lastname;
 $scope.names = names;
 $scope.jobs = jobs;
 });
 */

angular.module('myApp.controllers', []).controller('LoginController', function($scope) {
  /*
  отправляем данные из полей login и password на сервер(url: /login)
  если результат нормальный - перенаправляем на EnrolleesController иначе - выводим сообщение о неверном логине/пароле
   */
});
angular.module('myApp.controllers').controller('EnrolleesController', function($scope){

});
angular.module('myApp.controllers').controller('GuestController', function($scope){

});

