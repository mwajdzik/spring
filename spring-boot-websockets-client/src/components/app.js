import React, {Component} from "react";
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import MessageBar from "./message_bar";
import MessageList from "./message_list";

export default class App extends Component {

    constructor(props) {
        super(props);

        this.state = {messages: []};
        this.initializeWebSocketConnection()
    }

    initializeWebSocketConnection() {
        let serverUrl = 'http://localhost:8080/socket';
        let ws = new SockJS(serverUrl);
        this.stompClient = Stomp.over(ws);

        this.stompClient.connect({}, (frame) => {
            this.stompClient.subscribe("/chat", (message) => {
                if (message.body) {
                    this.setState({messages: [...this.state.messages, message.body]});
                }
            });
        });
    }

    sendMessage(message) {
        this.stompClient.send("/app/send/message", {}, message);
        this.stompClient.send("/app/send/message/private/room23", {}, message);
    }

    render() {
        return (
            <div className='container'>
                <div className='row'>
                    <MessageBar onMessageSubmit={(message) => this.sendMessage(message)}/>
                </div>
                <div className='row'>
                    <MessageList messages={this.state.messages}/>
                </div>
            </div>
        );
    }
}
