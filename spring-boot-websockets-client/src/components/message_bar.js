import React, {Component} from "react";

export default class MessageBar extends Component {

    constructor(props) {
        super(props);
        this.state = {message: ''};
    }

    render() {
        return (
            <form onSubmit={event => this.onSubmit(event)}>
                <div className="form-group">
                    <input type="text" className="form-control"
                           placeholder="Your message..."
                           onChange={event => this.onInputChange(event.target.value)}
                           value={this.state.message}/>
                </div>
            </form>
        );
    }

    onSubmit(event) {
        this.props.onMessageSubmit(this.state.message);
        this.setState({message: ''});
        event.preventDefault();
    }

    onInputChange(message) {
        this.setState({message});
    }
}
