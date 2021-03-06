'use strict';

angular.module('desappGrupoB022015FrontendApp')
.controller('LeagueCtrl', LeagueCtrl);


function LeagueCtrl($http,$scope, auth) {

	var lc = this;

  $scope.profile = auth.profile;

	$http.get('http://localhost:8080/desapp-grupoB022015-backend/rest/league/otherLeagues/'+$scope.profile.user_id).success(function (data) {
    	lc.leagues = data;
    });

    lc.editLeague = function(league) {
      location = '#/editLeague/' + league.id;
    };

    lc.showFixture = function(league) {
      location = '#/showFixture/' + league.id;
    };

    lc.addme = function(league) {
      $http.put('http://localhost:8080/desapp-grupoB022015-backend/rest/league/addUser/'+ $scope.profile.user_id + '/'+ league.id).success(function (data){
        location = '#/myLeagues';
      });
    };

}