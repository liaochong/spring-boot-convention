## 简介 | Brief introduction
spring-boot-convention是作者在多家公司获得的最佳实践合集，针对多项目的痛点，如不统一的输入、输出，大范围式的try/catch，hibernate validator校验力度范围不够、RPC share包pom内容重复定义严重等进行了底层处理，其能够达到的效果如下：
1. 极大降低代码量，通过统一异常设定以及统一异常拦截处理，使用异常流进行业务逻辑开发，业务流不需要关心返回值，也无需try/catch；
2. 降低RPC share包中pom内容的重复定义，如lombok依赖、上传仓库设定等；
3. 统一输入输出格式，已定义分页输入、分页输出、最终结果容器等，使用统一输入输出组件，对项目的后期维护均有极大益处；
4. 扩大hibernate validator参数校验范围，解除只能在controller中校验并且需要手动处理校验不通过数据的限制；

## 模块介绍 | Module introduce
* my-convention（规约父pom，定义下属模块通用配置，如build）
  - my-convention-api（api模块，只包含Paging、Result、PagingResult等输入输出基础类，独立出来方便集成到RPC依赖包中）
  - my-convention-common（common模块，包含规约异常定义，api模块通用的工具类，系统编码的定义）
  - my-convention-share-parent（share-parent模块，pom文件，定义RPC依赖包必须的依赖，如Hibernate validator、api模块、lombok等）
* my-spring-boot
  - my-spring-boot-parent
  - my-spring-boot-starters
    - my-spring-boot-starter-convention
* my-example

## 依赖关系 | Dependency relationship
* my-example
  - my-spring-boot-starter-parent
  - my-spring-boot-starter-convention
    - my-convention-common
      - my-convention-api
      
## 注意事项 | Attention
1. 所有依赖包均为上传到maven中央仓库，如需调试，需要本地install；
  - install my-convention;
  - install my-spring-boot-starter-parent;
  - install my-spring-boot-starters;
