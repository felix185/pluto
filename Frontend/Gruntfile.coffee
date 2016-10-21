module.exports = (grunt) ->

  # Initial definitions for folders
  OUTPUT_FOLDER = "./out"

  grunt.initConfig

    copy:
      static:
        options:
          flatten: true
        files: [
          # CSS Content
          {cwd: "./src/static/css", src: '**', dest: "#{OUTPUT_FOLDER}/css", expand: true}
          # HTML Content
          {cwd: "./src/static/html", src: '**', dest: "#{OUTPUT_FOLDER}", expand: true}
          # Image content
          {cwd: "./src/static/images", src: '**', dest: "#{OUTPUT_FOLDER}/img", expand: true}
          # Font content
          {cwd: "./src/static/fonts", src: '**', dest: "#{OUTPUT_FOLDER}/fonts", expand: true}
          # JS content
          {cwd: "./node_modules/json-formatter-js/dist", src: 'bundle.js', dest: "#{OUTPUT_FOLDER}/js", expand: true}
        ]

      training:
        options:
          flatten: true
        files: [
          {src: ['./build/tmp/compileBrowserify/dist.js'], dest: "#{OUTPUT_FOLDER}/js/training.js"}
        ]

    clean:
      browserify: ['./build/tmp/compileBrowserify']
      out: [OUTPUT_FOLDER]

    browserify:
      code:
        options:
          debug: true
          transform: [
            [
              'babelify',
              {
                presets: ["es2015"]
                plugins: ["transform-react-jsx", "transform-object-rest-spread"]
                global: true,
                babelrc: false
              }
            ]
          ]
        dest: 'out/js/training.js'
        src: ['src/main/**/*.js']
      watch:
        options:
          debug: true
          transform: [
            [
              'babelify',
              {
                presets: ["es2015"]
                plugins: ["transform-react-jsx", "transform-object-rest-spread"]
                global: true,
                babelrc: false
              }
            ]
          ]
          watch: true
          watchifyOptions:
            poll: true
          keepAlive: true
        dest: 'out/js/training.js'
        src: ['src/main/**/*.js']



  # Babel Conversion
  grunt.loadNpmTasks 'grunt-browserify'
  grunt.registerTask 'build-frontend', ['browserify:code']
  grunt.registerTask 'release-frontend', ['clean:out', 'build-frontend', 'copy:static']

  grunt.loadNpmTasks 'grunt-contrib-clean'
  grunt.loadNpmTasks 'grunt-contrib-copy'