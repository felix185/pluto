import React, {Component} from 'react';

export default class Projects extends Component {

  constructor(props) {
    super(props);

  }

  render() {
    return (
        <form method="post" action="http://localhost:8080/Pluto/laundry/createAlert">

            <input
                type="text"
            />

            <input type="submit" value="Post" />
        </form>
    );
  }
}
