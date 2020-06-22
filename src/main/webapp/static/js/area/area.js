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
                    this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
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
            let aObj = $("#" + treeNode.tId + "_a");
            if ($("#treeMenu_"+treeNode.id+"_add").length>0) return;
            let editStr = `<span class="button add" id="treeMenu_${treeNode.id}_add" title="add"  style=""></span>`;
            aObj.append(editStr);
            let span = $("#treeMenu_"+treeNode.id+"_add");
            if (span) span.bind("click", function(){alert("diy Button for " + treeNode.name);});
        },
        removeHoverDom:function (treeId,treeNode) {
            $("#treeMenu_"+treeNode.id+"_add").unbind().remove();
        }
    },
    created: function () {
        this.selectAll(1, this.pageInfo.pageSize);
    },
    mounted:function(){
        this.initTree();
    }
})