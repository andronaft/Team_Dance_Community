<template>
  <div class="home">

    <vueper-slides>
  <vueper-slide v-for="(slide, i) in slides" :key="i" :title="slide.title" :content="slide.content" />
</vueper-slides>
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  </div>
</template>

<script>
import { VueperSlides, VueperSlide } from 'vueperslides'


// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: { VueperSlides, VueperSlide },
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
