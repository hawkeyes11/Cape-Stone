Text165 lines3.9KB24 hoursTTL
Shades of Purple
raw
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
119
120
121
122
123
124
125
126
127
128
129
130
131
132
133
134
135
136
137
138
139
140
141
142
143
144
145
146
147
148
149
150
151
152
153
154
155
156
157
158
159
160
161
162
163
164
165
<template>
  <div>
    <div class="homepage-welcome">
      <h2>Welcome back {{ username }}!</h2>
    </div>
    <div class="options">
      <h3>Hungry?</h3>
      <button @click="$router.push('/meet')" class="custom-btn invite">
        <span>Find Food for You and Your Friends </span>
        <!-- redirects to Friends List ?? -->
      </button>
      <button @click="$router.push('/card')" class="custom-btn find-restaurant">
        <span>Find Food for You</span>
        <!-- redirects to Restaurant Cards -->
      </button>
      <!-- Figure out the social networking/matchmaking elements -->
      <button @click="showInput = !showInput" class="custom-btn final">
        <span> Go to Group List </span>
      </button>
      <div v-if="showInput">
        <label for="groupIdInput">Enter id of your group</label>
        <input v-model="groupId" id="groupIdInput" />
        <button
          class="custom-btn final"
          @click="$router.push({ name: 'groupLisp', params: { groupId } })"
        >
          Go to group finalist
        </button>
      </div>
      <!-- <button @click="$router.push('/')" class="custom-btn logout">
        <span>Logout</span>
      </button> -->
    </div>
  </div>
</template>

<script>
// import authService from "../services/AuthService";

export default {
  name: "home",
  data() {
    return {
      username: this.$store.state.user.username,
      showInput: false,
      groupId: "",
    };
  },
};
</script>

<style scoped>
.homepage-welcome {
  display: flex;
  justify-content: left;
  margin-left: 10%;
}

.options {
  display: flex;
  /* font-family: 'Yantramanav', sans-serif; */
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #fffddb;
  border: 2px solid #f2b705;
  width: 75%;
  height: 75%;
  border-radius: 15px;
  margin: auto;
  padding: 1%;
  height: auto;
  filter: drop-shadow(3px 3px 10px #000000);
}
button {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 75%;
  height: 100%;
  text-align: middle;
}
button span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.25em;
}
.custom-btn {
  display: flex;
  flex-shrink: 1;
  height: 75px;
  background-color: #f25c05;
  color: #000;
  border-radius: 5px;
  padding: 10px 25px 10px 25px;
  font-family: "Lato", sans-serif;
  font-weight: 500;
  /* background: transparent; */
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}
button:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image: linear-gradient(to left, #f25c05, #f2b705);
  border-radius: 5px;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

button:hover {
  color: #fff;
}
button:hover:after {
  left: 0;
  width: 100%;
}
button:active {
  top: 2px;
}
button.logout {
  background-color: #f21616;
  width: 25%;
  height: 50%;
}
button.logout:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image: linear-gradient(to left, black, #f21616);
  border-radius: 5px;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

button.logout:hover {
  color: #fff;
}
button.logout:hover:after {
  left: 0;
  width: 100%;
}
button.logout:active {
  top: 2px;
}
</style>
 © 2020 Josh Goebel
Powered by Node.js, Express, Highlight.js, and Love.