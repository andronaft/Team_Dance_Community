<template>
  <div class="timetable">
      
      <div class="header">
          <div class="time active">
              12:00
          </div>
          <div class="time">
              14:00
          </div>
          <div class="time">
              16:00
          </div>
          <div class="time">
              18:00
          </div>
          <div class="time">
              19:00
          </div>
          <div class="time">
              21:00
          </div>
          <div class="time">
              23:00
          </div>
      </div>
      <div class="body">
          <div class="item">
              <div class="item__time">
                  14:00
              </div>
              <div class="item__category">
                  jazz-funk набор
              </div>
              <div class="item__teacher">
                  Яруллина Ольга
              </div>
              <div class="item__level">
                  начальный
              </div>
          </div>
          <div class="item">
              <div class="item__time">
                  14:00
              </div>
              <div class="item__category">
                  jazz-funk набор
              </div>
              <div class="item__teacher">
                  Яруллина Ольга
              </div>
              <div class="item__level">
                  начальный
              </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.timetable {
    text-align: left;
    padding: 15px;
    background-color: #176777;
}
.header {
    display: flex;
    .time {
        padding: 10px;
        font-size: 24px;
        &:first-child {
            padding-left: 0;
        }
    }
}

.item {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
}
</style>
