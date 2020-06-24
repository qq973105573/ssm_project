let vm=new Vue({
    el:'#main-container',
    data:function () {
        return{
            setting: {
                data:{
                    key: {
                    },
                    simpleData:{
                        enable:true,
                        pIdKey:"parentId"
                    }
                },
                callback:{
                    onClick:this.onClick
                }
            },
            area:{}
        }
    },
    methods:{
        initTree:function () {
            axios({
                url:'manager/area/selectAll'
            }).then(response=>{
                let nodes=response.data;
                //动态添加根节点  原长度[0,length-1]
                nodes[nodes.length]={id:0,name:'区域列表',open:true};
                let treeObj = $.fn.zTree.init($('#select-tree'),this.setting,nodes);
            })
        },
        onClick: function (event,treeId,treeNode) {
            this.title = treeNode.name;//给页面的显示选中的节点名赋值
            // this.params.pid=treeNode.id;//给查询条件赋值
            //点击操作后，需要给父layer传递参数  父区域名、新的父区id
            parent.layer.obj=treeNode;//将新节点赋值给父layer的obj
            let frameIndex = parent.layer.getFrameIndex(window.name);
            parent.layer.close(frameIndex);
        }
    },
    created: function () {
    },
    mounted:function () {
        this.initTree();
    }
})