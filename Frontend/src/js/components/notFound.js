import React, {Component} from 'react';
import Notification from 'grommet/components/Notification';


export default class NotFound extends Component {

  constructor(props) {
    super(props);

  }

  render () {
    return(
        <Notification status='warning' message='The requested URL was not found!' />
    );
  }
}
