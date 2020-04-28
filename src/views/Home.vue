<template>
  <div class="home">

    <vueper-slides>
  <vueper-slide v-for="(slide, i) in slides" :key="i" :title="slide.title" :content="slide.content" />
</vueper-slides>
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->

      <div class="main">
        <aside>
          <div class="aside__block">
            <h3>Новости</h3>
            <div class="aside__block__body">
              <div class="latestnews">
               <div class="news__item">
                 <div class="date">07 апр</div><a href="#">Myway Dance Academy 2020: Виктория Ситник</a>
               </div>
              <div class="news__item">
                 <div class="date">07 апр</div><a href="#">Myway Dance Academy 2020: Виктория Ситник</a>
               </div>
              <div class="news__item">
                 <div class="date">07 апр</div><a href="#">Myway Dance Academy 2020: Виктория Ситник</a>
               </div>
              </div>
            </div>
          </div>
        </aside>
        <main>
          <h3>Расписание</h3>
            <Timetable/>
          <div class="bottom">
            <div class="boxes">
            <a href="#">Учителя</a>
            <a href="#">Студии</a>
            <a href="#">Видео</a>
            <a href="#">Одежда</a>
          </div>
          <div class="aboutus">
            Team Dance Community - танцевальная школа современной хореографии (hip-hop, contemporary, jazz-funk, house, ragga, popping, stomp) для людей любого возраста и уровней подготовки.
          </div>
          </div>
          
          </main>
      </div>
  </div>
</template>

<script>
import { VueperSlides, VueperSlide } from 'vueperslides'

import Timetable from '@/components/Timetable.vue'

// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: { VueperSlides, VueperSlide, Timetable },
  data: () => ({
    slides: [
    {
      title: 'Slide #1',
      content: 'Slide content.'
    }
  ]
  })
}
</script>


<style lang="scss" scoped>
  .main {
    display: grid;
    padding: 10px;
    grid-template-columns: 1fr;
    @include lg {
      grid-template-columns: 1fr 3fr;
    }
  }


  .latestnews {
    display: flex;
    flex-direction: column;
    .news__item {
      padding: 10px;
      display: flex;
    }
    a {
      color: #fff;
    }
  }

  .bottom {
    padding: 10px 0;
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
  .boxes {
    display: grid;
    grid-template-columns: 1fr 1fr;
    a {
      height: 100px;
      width: 150px;
    }
  }
</style>