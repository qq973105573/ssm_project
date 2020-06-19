let vm = new Vue({
    el:'#main-container',
    data:{
        obj:{
            workOrder:{},
            transfers:[],
            details:[]//初始化属性
        }
    },
    methods:{
        selectDetail:function(id){
            axios({
                url:`manager/work/selectDetail/${id}`,
            }).then(response=>{
                this.obj=response.data.obj;
            }).catch(error=>{
                layer.msg(error.message);
            })
        }
    },
    created:function () {
        this.selectDetail(parent.layer.obj);
    }
})