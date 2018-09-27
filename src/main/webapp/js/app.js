var app = angular.module('tsn', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/garant");

    $stateProvider
        .state('garant', {
            url: "/garant",
            templateUrl: "views/guest/guest.html",
            controller: ['$scope', '$state',
                function ($scope, $state) {
                    $state.go('garant.news');
                }]
        })
        .state('garant.news', {
            url: "/news",
            templateUrl: "views/guest/news.html",
            controller: 'NewsController'
        })
        .state('garant.regulations', {
            url: "/regulations",
            templateUrl: "views/guest/regulations.html"
        })
        .state('garant.contacts', {
            url: "/contacts",
            templateUrl: "views/guest/contacts.html"
        })
        .state('garant.documents', {
            url: "/documents",
            templateUrl: "views/guest/documents.html",
            controller: 'DocumentsController'
        })
        .state('garant.login', {
            url: "/login",
            templateUrl: "views/guest/login.html",
            controller: 'LoginController'
        })


        .state('admin', {
            url: "/admin",
            templateUrl: "views/admin/admin.html",
            controller: ['$scope', '$state',
                function ($scope, $state) {
                    $state.go('admin.news');
                }]
        })
        .state('admin.news', {
            url: "/news",
            templateUrl: "views/admin/news.html",
            controller: 'NewsController'
        })
        .state('admin.newNews', {
            url: "/new",
            templateUrl: "views/admin/new-news.html",
            controller: 'NewNewsController'
        })
        .state('admin.updateNews', {
            url: "/update-news/{id}",
            templateUrl: "views/admin/update-news.html",
            controller: 'UpdateNewsController'
        })
        .state('admin.users', {
            url: "/users",
            templateUrl: "views/admin/users.html",
            controller: 'UsersController'
        })
        .state('admin.newUser', {
            url: "/new-user",
            templateUrl: "views/admin/new-user.html",
            controller: 'NewUserController'
        })
        .state('admin.applications', {
            url: "/applications",
            templateUrl: "views/admin/applications.html",
            controller: 'ApplicationsController'
        })
        .state('admin.application', {
            url: "/application/{id}",
            templateUrl: "views/admin/application-details.html",
            controller: 'ApplicationDetailsController'
        })
        .state('admin.document-upload', {
            url: "/document-upload",
            templateUrl: "views/admin/document-upload.html",
            controller: 'DocumentUploadController'
        })
        .state('admin.logout', {
            url: "/log-out",
            templateUrl: "",
            controller: 'LogoutController'
        })


        .state('user', {
            url: "/user",
            templateUrl: "views/user/user.html",
            controller: ['$scope', '$state',
                function ($scope, $state) {
                    $state.go('user.applications');
                }]
        })
        .state('user.applications', {
            url: "/user-applications",
            templateUrl: "views/user/applications.html",
            controller: 'UserApplicationsController'
        })
        .state('user.newApplication', {
            url: "/new-application",
            templateUrl: "views/user/new-application.html",
            controller: 'NewApplicationController'
        })
        .state('user.application', {
            url: "/application/{id}",
            templateUrl: "views/user/application-details.html",
            controller: 'UserApplicationDetailsController'
        })
        .state('user.logout', {
            url: "/logout",
            templateUrl: "",
            controller: 'LogoutController'
        })
    ;
}]);


var controllers = {};

controllers.DocumentsController = function ($scope, $state, documentFactory) {
    $scope.documents = [];

    documentFactory.getAllDocumentsNames()
        .success(function (data) {
            $scope.documents = data;
        })
        .error(function (data, status) {

                swal({
                    title: "Error: " + status,
                    text: data,
                    type: "error",
                    html: true
                });

        });

    $scope.downloadDocument = function(document) {
        var win = window.open('getDocumentByName?documentName=' + document, '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Broswer has blocked it
            swal('Пожалйуста, разрешите всплывающие окна!', "warning");
        }
    }

};


controllers.DocumentUploadController = function ($scope, $state, documentFactory, $http) {
    $scope.documents = [];

    documentFactory.getAllDocumentsNames()
        .success(function (data) {
            $scope.documents = data;
        })
        .error(function (data, status) {
            if (status == 401) {
                swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
            } else {
                swal({
                    title: "Error: " + status,
                    text: data,
                    type: "error",
                    html: true
                });
            }
        });


    $scope.deleteDocument = function (documentName) {

        swal({
                title: "Вы уверены?",
                text: "Документ будет безвозвратно удален!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Да, удалить!",
                cancelButtonText: "Нет, отменить!",
                closeOnConfirm: false
            },
            function () {
                var win = window.open('authorized/deleteDocumentByName?documentName=' + documentName, '_blank');
                if (win) {
                    //Browser has allowed it to be opened
                    win.focus();
                } else {
                    //Broswer has blocked it
                    swal('Пожалйуста, разрешите всплывающие окна!', "warning");
                }
            }
                /*$http.get()('/authorized/deleteDocumentByName?documentName=' + documentName)
                success(function () {
                    swal({
                            title:"Удалено!",
                            text: "",
                            type: "success"
                        },
                        function () {
                            $state.go($state.current, {}, {reload: true}); //second parameter is for $stateParams
                        });

                })
                    .error(function (data, status) {
                        swal("Error: " + status, JSON.stringify(data), "error");
                    });
            }*/
        );


        /*var win = window.open('tsn-sevas.rhcloud.com/authorized/deleteDocumentByName?documentName=' + documentName, '_blank');
         if (win) {
         //Browser has allowed it to be opened
         win.focus();
         } else {
         //Broswer has blocked it
         swal('Пожалйуста, разрешите всплывающие окна!', "warning");
         }
         */
        /* documentFactory.deleteDocument(documentName)
         .success(function (data) {
         swal("Готово", JSON.stringify(data), "warning");
         })
         .error(function (data, status) {
         if (status == 401) {
         swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
         } else {
         swal({
         title: "Error: " + status,
         text: data,
         type: "error",
         html: true
         });
         }
         });*/
    };


    $scope.downloadDocument = function (documentName) {

        var win = window.open('getDocumentByName?documentName=' + documentName, '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Broswer has blocked it
            swal('Пожалйуста, разрешите всплывающие окна!', "warning");
        }
        /*documentFactory.getDocumentByName(documentName)
         .success(function () {

         })
         .error(function (data, status) {
         if (status == 401) {
         swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
         } else {
         swal({
         title: "Error: " + status,
         text: data,
         type: "error",
         html: true
         });
         }
         });*/
    };


};

controllers.ApplicationDetailsController = function ($scope, $state, $stateParams, applicationFactory, userFactory) {
    $scope.application = {};
    $scope.newComment = {};
    $scope.author = {};

    applicationFactory.getApplicationById($stateParams.id)
        .success(function (data) {
            $scope.application = data;
        })
        .error(function (data, status) {
            if (status == 401) {
                swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
            } else {
                swal({
                    title: "Error: " + status,
                    text: data,
                    type: "error",
                    html: true
                });
            }
        });

    /* userFactory.getUserByLogin(application.authorName)
     .success(function (data) {
     $scope.author = data;
     })
     .error(function (data, status) {
     if (status == 401) {
     swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
     } else {
     swal({
     title: "Error: " + status,
     text: data,
     type: "error",
     html: true
     });
     }
     });*/

    $scope.addNewComment = function (newComment) {
        var applicationIdAndCommentText = {};
        applicationIdAndCommentText.applicationId = $stateParams.id;
        applicationIdAndCommentText.commentText = newComment;


        applicationFactory.addCommentToApplication(applicationIdAndCommentText)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Комментарий отправлен!",
                    type: "success"
                }, function () {
                    $state.go('admin.applications');
                });
            })
            .error(function (data, status) {
                if (status == 401) {
                    swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
                } else {
                    swal({
                        title: "Error: " + status,
                        text: data,
                        type: "error",
                        html: true
                    });
                }
            });
    };

};

controllers.UserApplicationDetailsController = function ($scope, $state, $stateParams, applicationFactory) {
    $scope.application = {};
    $scope.newComment = {};

    applicationFactory.getApplicationById($stateParams.id)
        .success(function (data) {
            $scope.application = data;
        })
        .error(function (data, status) {
            if (status == 401) {
                swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
            } else {
                swal({
                    title: "Error: " + status,
                    text: data,
                    type: "error",
                    html: true
                });
            }
        });

    $scope.addNewComment = function (newComment) {
        var applicationIdAndCommentText = {};
        applicationIdAndCommentText.applicationId = $stateParams.id;
        applicationIdAndCommentText.commentText = newComment;


        applicationFactory.addCommentToApplication(applicationIdAndCommentText)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Комментарий отправлен!",
                    type: "success"
                }, function () {
                    $state.go('user.applications');
                });
            })
            .error(function (data, status) {
                if (status == 401) {
                    swal("Ошибка доступа!", " Неодходимо авторизироваться!", "error");
                } else {
                    swal({
                        title: "Error: " + status,
                        text: data,
                        type: "error",
                        html: true
                    });
                }
            });
    };

    $scope.deleteApplication = function () {
        swal({
                title: "Вы уверены?",
                text: "Ваше заявление будет безвозвратно удалено!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Да, удалить!",
                cancelButtonText: "Нет, отменить!",
                closeOnConfirm: false
            },
            function () {
                applicationFactory.deleteApplication(application)
                    .success(function () {
                        swal({
                            title: "Удалено!",
                            type: "success"
                        }, function () {
                            $state.go('user.applications');
                        });
                    })
                    .error(function (data, status) {
                        if (status == 401) {
                            swal("Ошибка доступа!", " Необходимо авторизироваться!", "error");
                        } else {
                            swal({
                                title: "Error: " + status,
                                text: data,
                                type: "error",
                                html: true
                            });
                        }
                    });
            });

    };
};

controllers.UserApplicationsController = function ($scope, $state, applicationFactory) {
    $scope.applications = [];

    applicationFactory.getAllUserApplications()
        .success(function (data) {
            $scope.applications = data;
        })
        .error(function (data, status) {
            swal("Error: " + status, JSON.stringify(data), "error");
        });


};

controllers.ApplicationsController = function ($scope, $state, applicationFactory) {
    $scope.applications = [];


    applicationFactory.getAllApplications()
        .success(function (data) {
            $scope.applications = data;
        })
        .error(function (data, status) {
            if (status == 401) {
                swal("Ошибка доступа!", " Необходимо авторизироваться!", "error");
            } else {
                swal({
                    title: "Error: " + status,
                    text: data,
                    type: "error",
                    html: true
                });
                //swal("Error: " + status, JSON.stringify(data), "error");
            }
        });

};

controllers.NewApplicationController = function ($scope, $state, applicationFactory) {

    $scope.application = {};

    $scope.addApplication = function () {
        applicationFactory.addApplication($scope.application)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Заявка отправлена!",
                    type: "success"
                }, function () {
                    $state.go('user.applications')
                });
            })
            .error(function (data, status) {
                swal("Error: " + status, JSON.stringify(data), "error");
            });
    };

    $scope.cancelApplicationCreation = function () {
        $state.go('user.applications');
    };
};

controllers.NewsController = function ($scope, newsFactory) {
    $scope.articles = [];

    newsFactory.getAllNews()
        .success(function (data) {
            $scope.articles = data;
        })
        .error(function (data, status) {
            swal("Error: " + status, JSON.stringify(data), "error");
        });

};

controllers.NewNewsController = function ($scope, $state, newsFactory) {

    $scope.article = {};

    $scope.addNews = function () {
        newsFactory.addNews($scope.article)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Новость добавлена!",
                    type: "success"
                }, function () {
                    $state.go('admin.news')
                });
            })
            .error(function (data, status, headers, config) {
                swal("Error: " + status, JSON.stringify(data), "error");
            });
    };

    $scope.cancelNewsCreation = function () {
        $state.go('admin.news');
    };
};

controllers.UpdateNewsController = function ($scope, $stateParams, $state, newsFactory) {

    $scope.article = {};

    newsFactory.getNews($stateParams.id)
        .success(function (data) {
            $scope.article = data;
        })
        .error(function (data, status, headers, config) {
            swal("Error: " + status, JSON.stringify(data), "error");
        });


    $scope.updateNews = function () {
        newsFactory.updateNews($scope.article)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Новость обновлена!",
                    type: "success"
                }, function () {
                    $state.go('admin.news');
                });
            })
            .error(function (data, status) {
                swal("Error: " + status, JSON.stringify(data), "error");
            });
    };

    $scope.deleteNews = function () {
        newsFactory.deleteNews($scope.article)
            .success(function () {
                swal({                                   // TODO: добавить sweetAlert действительно удалить?
                    title: "Готово!",
                    text: "Новость удалена!",
                    type: "success"
                }, function () {
                    $state.go('admin.news')
                });
            })
            .error(function (data, status) {
                swal("Error: " + status, JSON.stringify(data), "error");
            });
    };

};

controllers.UsersController = function ($scope, $state, userFactory) {
    $scope.users = [];

    userFactory.getAllUsers()
        .success(function (data) {
            $scope.users = data;
        })
        .error(function (data, status) {
            swal("Error: " + status, JSON.stringify(data), "error");
        });

    $scope.deleteUser = function (user) {
        swal({
                title: "Вы уверены?",
                text: "Информация о пользователе будет безвозвратно удалена!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Да, удалить!",
                cancelButtonText: "Нет, отменить!",
                closeOnConfirm: false
            },
            function () {
                userFactory.deleteUser(user)
                    .success(function () {
                        swal({
                                title:"Удалено!",
                                text: "",
                                type: "success"
                            },
                            function () {
                                $state.go($state.current, {}, {reload: true}); //second parameter is for $stateParams
                            });

                    })
                    .error(function (data, status) {
                        swal("Error: " + status, JSON.stringify(data), "error");
                    });
            });

    };

};

controllers.NewUserController = function ($scope, $state, userFactory) {

    $scope.user = {};

    $scope.addUser = function () {
        userFactory.addUser($scope.user)
            .success(function () {
                swal({
                    title: "Готово!",
                    text: "Пользователь добавлен!",
                    type: "success"
                }, function () {
                    $state.go('admin.users')
                });
            })
            .error(function (data, status, headers, config) {
                swal("Error: " + status, JSON.stringify(data), "error");
            });
    };

    $scope.cancelUserCreation = function () {
        $state.go('admin.users');
    };

};
/*
 controllers.UpdateUserController = function ($scope, $stateParams, $state, userFactory) {

 $scope.user = {};

 userFactory.getUserById($stateParams.id)
 .success(function (data) {
 $scope.user = data;
 })
 .error(function (data, status) {
 swal("Error: " + status, JSON.stringify(data), "error");
 });


 $scope.test = function () {
 userFactory.getUserById($stateParams.id)
 .success(function (data) {
 swal({
 title: "Готово!",
 text: JSON.stringify(data),
 type: "success"
 });
 })
 .error(function (data, status) {
 swal("Error: " + status, JSON.stringify(data), "error");
 });
 };


 $scope.updateUser = function () {
 userFactory.updateUser($scope.user)
 .success(function () {
 swal({
 title: "Готово!",
 text: "Пользователь обновлен!",
 type: "success"
 }, function() {
 $state.go('admin.users');
 });
 })
 .error(function (data, status) {
 swal("Error: " + status, JSON.stringify(data), "error");
 });
 };

 $scope.deleteUser = function () {
 userFactory.deleteUser($scope.user)
 .success(function () {
 swal({                                   // TODO: добавить sweetAlert действительно удалить?
 title: "Готово!",
 text: "Пользователь удален!",
 type: "success"
 }, function () {
 $state.go('admin.users')
 });
 })
 .error(function (data, status) {
 swal("Error: " + status, JSON.stringify(data), "error");
 });
 };

 };*/

controllers.LoginController = function ($scope, $http, $state) {

    $state.go('garant.login');

    $scope.login = function ($state) {

        var dataObj = {
            login: $scope.userLogin,
            password: $scope.userPassword
        };
        var res = $http.post('/login', dataObj);
        res.success(function (data) {
            //$scope.changeState();

            if (data == "Admin") {
                location.href = "http://tsn.herokuapp.com/#/admin/news";
            } else {
                location.href = "http://tsn.herokuapp.com/#/user/user-applications";
            }

            //swal("Success!", "Now you are logged in! " + JSON.stringify(data), "success");*/
        });
        res.error(function (data, status) {
            swal("Error: " + status, JSON.stringify(data), "error");
        });
    }
};


controllers.LogoutController = function ($scope, $http) {

    var res = $http.get('/logout');
    res.success(function () {
        location.href = "http://tsn.herokuapp.com/#/garant/news";
    });
    res.error(function (data, status) {
        swal("Error: " + status, JSON.stringify(data), "error");
    });

};

app.controller(controllers);


app.factory('newsFactory', function ($http) {

    var newsFactory = {};

    newsFactory.getAllNews = function () {
        return $http.get('/getAllNews');
    };
    newsFactory.getNews = function (id) {
        return $http.post('/authorized/getNews', id);
    };

    newsFactory.addNews = function (news) {
        return $http.post('/authorized/addNews', news);
    };

    newsFactory.updateNews = function (news) {
        return $http.post('/authorized/updateNews', news)
    };

    newsFactory.deleteNews = function (news) {
        return $http.post('/authorized/deleteNews', news);
    };

    return newsFactory;
});

app.factory('userFactory', function ($http) {

    var userFactory = {};

    userFactory.getAllUsers = function () {
        return $http.get('/authorized/getAllUsers');
    };

    userFactory.getUserById = function (id) {
        return $http.post('/authorized/getUserById/', id);
    };

    userFactory.getUserByLogin = function (login) {
        return $http.post('/authorized/getUserByLogin/', login);
    };

    userFactory.addUser = function (user) {
        return $http.post('/authorized/addUser', user);
    };

    userFactory.updateUser = function (user) {
        return $http.post('/authorized/updateUser', user);
    };

    userFactory.deleteUser = function (user) {
        return $http.post('/authorized/deleteUser', user);
    };

    return userFactory;
});


app.factory('applicationFactory', function ($http) {

    var applicationFactory = {};

    applicationFactory.getAllApplications = function () {
        return $http.get('/authorized/getAllApplications');
    };

    applicationFactory.getAllUserApplications = function () {
        return $http.get('/authorized/getAllUserApplications');
    };

    applicationFactory.addApplication = function (application) {
        return $http.post('/authorized/addApplication', application);
    };

    applicationFactory.getApplicationById = function (applicationId) {
        return $http.post('/authorized/getApplicationById', applicationId);
    };


    applicationFactory.addCommentToApplication = function (applicationAndComment) {
        return $http.post('/authorized/addCommentToApplication', applicationAndComment);
    };

    applicationFactory.deleteApplication = function (application) {
        return $http.post('/authorized/deleteApplication', application);
    };

    return applicationFactory;
});


app.factory('documentFactory', function ($http) {

    var documentFactory = {};

    documentFactory.getAllDocumentsNames = function () {
        return $http.get('/allDocuments');
    };

    documentFactory.getDocumentByName = function (documentName) {
        return $http.get('/getDocumentByName', {
            params: {
                documentName: documentName
            }
        });
    };

    documentFactory.deleteDocument = function (documentName) {
        return $http.get()('/authorized/deleteDocumentByName', {
            params: {documentName: documentName}
        });
    };

    return documentFactory;
});