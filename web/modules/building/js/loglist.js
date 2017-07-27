angular.module("myApp").controller("building.loglist", ['$scope','$uibModalInstance','$uibModal','$http','toaster','confirmDialog','uploadFiles','$ocLazyLoad','$state','obj',function($scope,$uibModalInstance,$uibModal,$http,toaster,confirmDialog,uploadFiles,$ocLazyLoad,$state,obj){
    $scope.columns=[
        {name:"编号",width:"5%",columnName:"id"},
        {name:"内容",width:"30%",columnName:"logText"},
        {name:"姓名",width:"5%",columnName:"operater"},
        {name:"时间",width:"15%",columnName:"createtime"},

    ];
    $scope.hidePage="true";

    $scope.pageOption={"currentPage":1,"pageSize":12,"relateId":"office_"+obj.officeid};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.deleteBatch=function(){
    	confirmDialog("删除","确定删除吗？",function () {
        	var jsonData={ids:$scope.selected.join(",")};
        	$http.post('building/deleteByIds.do',jsonData).success(function(){
        		refreshTable();
        	});
        });
    }
    
    $scope.clickOperate=function(id,type){
    		confirmDialog("删除","确定删除吗？",function () {
    			deleteE(id);
            });

    }

    function deleteE(id){
    	var jsonData={"id":id};
    	$http.post('building/delete.do',jsonData).success(function(){
    		refreshTable();
    	});
    }
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }

    function refreshTable(){
    	$http.post('buildinglog/loadPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }

    $scope.ok = function () {
        $uibModalInstance.dismiss('cancel');
    };

    refreshTable();

}]);