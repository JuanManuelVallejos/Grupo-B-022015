'use strict';

/**
 * @ngdoc function
 * @name desappGrupoB022015FrontendApp.controller:UpdateRoundCtrl
 * @description
 * # UpdateRoundCtrl
 * Controller of the desappGrupoB022015FrontendApp
 */
angular.module('desappGrupoB022015FrontendApp')
  .controller('UpdateRoundCtrl', function ($scope,$http) {

    var up = this;

    $http.get('http://localhost:8080/desapp-grupoB022015-backend/rest/realTeam/list').success(function (data) {
      $scope.teams = data;
    });

    $http.get('http://localhost:8080/desapp-grupoB022015-backend/rest/league/tournamentBegan').success(function (data) {
      $scope.began = data;
    });


    up.increaseDate = function(){
      $http.put('http://localhost:8080/desapp-grupoB022015-backend/rest/league/increaseCurrentDate')
      .success(function(data) {
        alert('Se cambio de fecha exitosamente');
        }).error(function(data,status) {
            alert('No se pudo cambiar de fecha, error (' + status + ')');
        });
    }
    
    $scope.updateDateForUsers = function(){
      $http.post('http://localhost:8080/desapp-grupoB022015-backend/rest/user/updateDateForUsers').success(function(data){
        up.increaseDate();
        alert();
      });
    }

    $scope.runLeagues = function(){
      $http.put('http://localhost:8080/desapp-grupoB022015-backend/rest/league/initializeFixtures')
      .success(function(data) {
        $scope.began = true;
        alert('Empezo el torneo');
        }).error(function(data,status) {
            alert('No pudo arrancar el torneo, error (' + status + ')');
        });
    }

    $scope.searchPlayers = function() {
       $http.get('http://localhost:8080/desapp-grupoB022015-backend/rest/realTeam/getPlayers/'+ $scope.playerteam +'/' + $scope.playerposition).success(function (data) {
            $scope.players = data;
        });
    }

    $scope.actualizatePlayer = function(){
        $http.put('http://localhost:8080/desapp-grupoB022015-backend/rest/player/updatePoints/'+ $scope.playerid +'/' + $scope.goals).success(function (data) {
            alert('Se actualizo correctamente el jugador');
        });
    }


});
