/** @type {import('tailwindcss').Config} */
// const colors = require('tailwindcss/colors')

module.exports = {
  content: [
    "./App.{js,jsx,ts,tsx}",
    "./src/screens/**/*.{js,jsx,ts,tsx}",
    "./src/components/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        blue: {
          pantone: '#001AA8',
          RYB: '#1A54F8',
          yonder: '#9FB3D2',
          oxford: '#08155D',
          cornflower: '#5C93FA',
        },
        lotion: '#FBFBFC',
        vampire: '#03040B',
      },
    },
  },
  plugins: [],
}
