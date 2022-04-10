# ImageSearcher. Master has stable app. Some features in develop.

2021 Pet-project.
MVVM, SingleActivity, Paging3, RxJava3 (instead of Coroutines), Hilt DI, Room, Gson, Multi-Modules, CI-CD

### ATTENTION
For building project you need to create apikeys.properties with 2 values "unsplash.AccessKey" and "unsplash.SecretKey".
Create own in [unsplash.com](https://unsplash.com/)

Need to fix tests for RxJava3

### Modules:
- [util](./util)
- [domain](./domain)
- [navigation](./navigation)
- [view_photo](./view_photo)
- [photos](./photos)
- [app](./app)

### Project has UnitTest with Coroutines:
[PhotosViewModelTest](./photos/src/test/java/com/shlyankin/photos/PhotosViewModelTest.kt)

### Tasks:
1) Create Model Layer with Paging (ready)
2) Create Photos List Screen (ready)
3) Create Favourite List Screen (ready)
4) Multi-Modules (ready)
5) Unit Tests (in progress. Need to fix tests for RxJava3)
6) CI-CD config (ready)
7) Create About Photo Screen (ready)
------------------------------------------
8) Create Searching (maybe later)
9) Customize UI (in progress)
10) Add filters and other features (maybe later)
