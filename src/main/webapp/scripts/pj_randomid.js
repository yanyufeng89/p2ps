//随机生成ID
function generate_field_id() {
	      var new_id;
	      new_id = generate_random_id();
	      return new_id;
	    };
function generate_random_id() {
        var string;
        string = "sel" + this.generate_random_char() + this.generate_random_char() + this.generate_random_char();
        while ($("#" + string).length > 0) {
          string +=generate_random_char();
        }
        return string;
      };
function generate_random_char() {
        var chars, newchar, rand;
        chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZ";
        rand = Math.floor(Math.random() * chars.length);
        return newchar = chars.substring(rand, rand + 1);
      };   