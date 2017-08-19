angular.module("myApp").controller("building.officelist", ['$scope','$uibModal','$http','toaster','confirmDialog','uploadFiles','$ocLazyLoad','$state',function($scope,$uibModal,$http,toaster,confirmDialog,uploadFiles,$ocLazyLoad,$state){
    $scope.name="xieyaxiong";
    $scope.selected=[];
    $scope.updateSelection = function($event, id){
    	var checkbox = $event.target;
    	if(id=='all'){
    		 $scope.selected=[];
    		$("input[name='cbname']").each(function(){
    			$(this).prop("checked",checkbox.checked); 
    			if(checkbox.checked){
    				$scope.selected.push($(this).attr("cid"));
    			}
    		})
    		return ;
    	}
    	var action = (checkbox.checked?'add':'remove');
    	if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
        }
    }
    $scope.columns=[
        {name:"编号",width:"10%",columnName:"id"},
        {name:"房源名称",width:"15%",columnName:"title"},
        {name:"所属楼盘",width:"15%",columnName:"publishOfficebuildinglistByOfficeBuildingId",relateName:"title"},
        {name:"地址",width:"10%",columnName:"publishOfficebuildinglistByOfficeBuildingId",relateName:"address"},
        {name:"房东姓名",width:"10%",columnName:"ownerName"},
        {name:"房东电话",width:"10%",columnName:"ownerTelephone"},
        {name:"价格",width:"10%",columnName:"rentPrice"},
        {name:"状态",width:"10%",columnName:"commonDataStatusByStatusId",relateName:"statusName"},

    ];
	$scope.operateWidth="10%";
    $scope.operations=[
        {name:"addLog",title:"添加日志",imgClass:"fa fa-plus"},
        {name:"showLog",title:"查看日志",imgClass:"fa fa-list"},
        {name:"uploadFile",title:"上传图片",imgClass:"fa fa-upload"},
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"},
        {name:"deleteImage",title:"删除图片",imgClass:"fa fa-times"},
        {name:"look",title:"查看",imgClass:"fa fa-share"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":12};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.deleteBatch=function(){
    	confirmDialog("删除","确定删除吗？",function () {
        	var jsonData={ids:$scope.selected.join(",")};
        	$http.post('office/deleteByIds.do',jsonData).success(function(){
        		refreshTable();
        	});
        });
    }
    
    $scope.clickOperate=function(id,type){
    	if(type=="deleteT"){
    		confirmDialog("删除","确定删除吗？",function () {
    			deleteE(id);
            });
    	}else if(type=="addLog"){
    	    addLog(id);
        }else if(type=="showLog"){
    	    showLog(id);
        }else if (type=="uploadFile"){
            showUploadFile(id)
        }else if(type=="look"){
            window.open("http://www.yooweel.com/frontsite/office/"+id);
        }else if(type=="deleteImage"){
            confirmDialog("删除","确定删除吗？",function () {
                deleteImage(id)
            });
        }else{
    		edit(id);
    	}
    }

    function showUploadFile(id){
            var modalInstance = $uibModal.open({
                templateUrl: 'modules/building/uploadFile.html',
                controller: 'building.uploadFile',
                size: "lg",
                resolve: {
                    obj: function () {
//                    return {categoryid:selectnode[0].id};
                        return {id:id,"tablename":"office"};
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/building/js/uploadFile.js");
                    }
                }
            });
    }

    function showLog(id){
        var modalInstance = $uibModal.open({
            templateUrl: 'modules/building/loglist.html',
            controller: 'building.loglist',
            size: "",
            resolve: {
                obj: function () {
                    return {officeid:id};
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("modules/building/js/loglist.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {

        });
    }
    
    function edit(id){
        $state.go('building/addOffice', {id: id})
    }


    function deleteE(id){
    	var jsonData={"id":id};
    	$http.post('office/delete.do',jsonData).success(function(){
    		refreshTable();
    	});
    }
    function deleteImage(id){
        var jsonData={"relateid":id,"tablename":"office"};
        $http.post('building/deleteImages.do',jsonData).success(function(){
        });
    }
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }

    function addLog(id){
        var modalInstance = $uibModal.open({
            templateUrl: 'common/inputName.html',
            controller: 'common.inputName',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"新增日志","content":""}
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("common/js/inputname.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
            saveLog(id,obj);
        });
    }

    function saveLog(id,name){
        var jsonData={"relateId":"office_"+id,"logText":name};
        $http.post('buildinglog/save.do',jsonData).success(function(){
            toaster.pop('success',"保存成功")
        });
    }

    function refreshTable(){
    	$http.post('office/loadPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }

    function loadArea(){
        $http.post('building/loadAllArea.do').success(function(data) {
            $scope.arealist=data

        });
    }
    function loadSubway(){
        $http.post('building/loadAllSubway.do').success(function(data) {
            $scope.subwaylist=data
        });
    }
    function loadStatus(){
        $http.post('building/loadAllStatus.do').success(function(data) {
            $scope.statuslist=data
        });
    }
    refreshTable();
    loadArea();
    loadSubway();
    loadStatus();

    $scope.pageOption.areaid="0";
    $scope.pageOption.subwayid="0";
    $scope.pageOption.statusid="0";
    
}]);