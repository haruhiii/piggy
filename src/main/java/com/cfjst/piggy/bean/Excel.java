package com.cfjst.piggy.bean;
 
import java.io.Serializable;
import java.util.Objects;
 

 
@Entity
@Table(name = "test", schema = "project")
public class Excel implements Serializable {
	
        private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=36)
	private String id;
	
	@Column(length=45,nullable=false,unique=true)
        private String username;
	
	
	
	@Column(length=45,nullable=false)
        private String password;
	
	@Column(length=45)
	private String role;
 
        public Excel() {
        }
 
        public Excel(Excel user){
            this.id = user.getId();
            this.username = user.getUsername();
            this.role = user.getRole();
            this.password = user.getPassword();
        }
         这个excel存储什么的
         用户？那谁提出的导入excel？需求都不知道那我就随便实现导入了  不拘泥excel中的内容了
        //get 和 set

        // 问题太多了 - -
        // 你这个还是在你自己eclipse里调试吧...
        //    我也不知道啊...我不熟悉啊...
        // 
}