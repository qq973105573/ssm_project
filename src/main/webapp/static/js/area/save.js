let vm = new Vue({
    el:'#main-container',
    data:{
        area:{}
    },
    methods:{
        doUpdate:function () {

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
        }
    },
    created:function () {
        this.area=parent.layer.obj;
    }
})