Android application to consume and display data from OkHttp Issues API 

I've used Clean Architecture with MVVM to develop this project. The project package hierarchy is as follows:

* di
* data
  * local
  * remote
  * repository (implementations)
  * paging_data_source
* domain
  * model
  * repository (interface)
  * usecase
* presentation

I used separate data classes for the DTOs, Room Database entities & Presentation models. 
This allowed to seamlessly develop the business logic and UI separately and allows me to make changes in either one of them without affecting the other

Libraries Used:
* Hilt
* Room
* Paging
* Retrofit
* Glide
* Markwon
