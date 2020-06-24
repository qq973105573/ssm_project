let vm = new Vue({
    el:'#main-container',
    data:function () {
        return{
            pageInfo:{
                pageNum:1,
                pageSize:5
            },
            setting:{ // 通过传入一维数组来实现自动组装成树形结构（自动转多维数组遍历）
                data:{
                    simpleData:{
                        enable:true, // 设置简单的数据库格式，可以使用一维数组的节点数据
                        pIdKey:'parentId'
                    }
                },
                callback:{
                    onClick:this.onClick,
                    beforeEditName:this.beforeEditName
                },
                view:{//自定义添加节点
                    addHoverDom:this.addHoverDom,
                    removeHoverDom:this.removeHoverDom
                },
                edit:{
                    enable: true
                }
            },
            nodes:[],
            treeObj:{},
            params:{
                areaName:'',
                aid:''
            }
        }
    },
    methods: {
        selectAll:function (pageNum,pageSize) {
            axios({
                url:`manager/area/selectPage/${pageNum}/${pageSize}`,
                method:'post',
                data:this.params
            }).then(response=>{
                this.pageInfo=response.data.obj;
            }).catch(error=>{
                console.log(error.message)
            })
        },
        selectAreaName:function () {//根据父区域的名查询
            this.params.aid='';//初始化aid
            this.selectAll(1,this.pageInfo.pageSize);
        },
        toUpdate:function (obj) {
            layer.obj=obj;//返回数据，绑定到layer上，传递给子窗口
            let index = layer.open({
                type:2,
                title:'区域修改',
                content:'manager/area/toUpdate',
                area:['80%','80%'],
                end:()=>{
                    if(!layer.success){
                        this.selectAll(1,this.pageInfo.pageSize);
                    }
                }
            })
        },
        initTree:function(){//初始化ztree
            //获取nodes
            axios({
                url:'manager/area/selectAll'
            }).then(response => {
                this.nodes = response.data;//   this.setNodes(.....)
                this.treeObj = $.fn.zTree.init($("#treeMenu"),this.setting,this.nodes);
            }).catch(function (error) {
                layer.msg(error.message);
            })
        },
        onClick:function(event, treeId, treeNode){
            this.params.aid=treeNode.id;
            this.params.areaName='';
            this.selectAll(1, this.pageInfo.pageSize)
        },
        beforeEditName:function (treeId, treeNode) {//结合开启修改节点按钮、点击修改按钮事件回调处理更新节点弹框
            return false;//阻止进入修改节点状态
        },
        addHoverDom:function (treeId,treeNode) {
            let aObj = $("#" + treeNode.tId + "_a");//获取li标签的id
            if ($("#treeMenu_"+treeNode.id+"_add").length>0) return;
            let editStr = `<span class="button add" id="treeMenu_${treeNode.id}_add" title="add" style=""></span>`;
            aObj.append(editStr);
            let span = $("#treeMenu_"+treeNode.id+"_add");
            if (span) span.bind("click", function(){
                alert("diy Button for " + treeNode.name);
            });
        },
        removeHoverDom:function (treeId,treeNode) {
            $("#treeMenu_"+treeNode.id+"_add").unbind().remove();
        },
        downloadFile:function () {
            location.href="manager/area/download";
        },
        /**
         * ajax文件上传：
         * 1.通过事件对象获取事件源中的文件对象
         * 2.创建FormData对象，将文件对象放入FormData对象
         * 3.设置ajax的请求header为multipart/form-data,请求方式为Post
         * 4.ajax发送请求，传输FormData对象
         */
        uploadFile:function(e){
            console.log(e.target.files[0]);
            let file = e.target.files[0];
            let form = new FormData();
            form.append("file",file);//"file"需要于后台接受参数名一致
            axios({
                url:'manager/area/upload',
                method:'post',
                data:form,
                headers:{'content-type':'multipart/form-data'}
            }).then(response=>{
                layer.msg(response.data.msg);
            }).catch(error=>{
                layer.msg(error.message);
            })
        }
    },
    created: function () {
        this.selectAll(1, this.pageInfo.pageSize);
    },
    mounted:function(){
        this.initTree();
    }
})