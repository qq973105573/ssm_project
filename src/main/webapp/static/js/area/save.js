let vm = new Vue({
    el:'#main-container',
    data:{
        area:{}
    },
    methods:{
        doUpdate:function () {
            axios({
                url:'manager/area/doUpdate',
                method:'put',
                data:this.area
            }).then(response=>{
                if(!response.data.success){
                    //失败需要重新同步页数据
                    parent.layer.success=false;
                }
                let index = parent.layer.getFrameIndex(window.name);//根据子窗口名获取索引值
                parent.layer.close(index);//通过父窗口layer对象关闭子窗口
                parent.layer.msg(response.data.msg);
            }).catch(error=>{//后台抛出错误无法被ExceptionController捕获处理的时候进入
                layer.msg(error.message);
            })
        },
        toIconPage:function () {
            let index = layer.open({
                type:2,
                title:'区域图标修改',
                content:'manager/area/awesome',
                area:['80%','80%'],
                end:()=>{
                    if(layer.icon!=null){
                        this.area.icon=layer.icon;
                    }
                }
            });
        },
        toSelect:function () {
            layer.open({
                type:2,
                content:'manager/area/toSelect',
                title:'选择父级区域',
                area:['100%','100%'],
                end:()=>{
                    //父节点发生改变才进行更新
                    if(layer.obj!=undefined&&layer.obj.id!=this.area.parentId){
                        //设置新的parent_ids和parent_id 和parentName
                        this.area.parentId=layer.obj.id;;//将子窗口选择的父区域的id赋值给当前area的parentId
                        this.area.parentName=layer.obj.name;
                        //设置新的parent_ids parent_ids=新的父id+新的父ids
                        this.area.parentIds=layer.obj.parentIds+layer.obj.id+",";
                    }
                }
            })
        },
        //取消按钮逻辑
        toClose:function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    created:function () {
        this.area=parent.layer.obj;
        this.area.oldParentIds=this.area.parentIds;//给oldParentIds赋值
    }
})