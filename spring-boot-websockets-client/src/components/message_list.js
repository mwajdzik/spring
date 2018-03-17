import React from "react";
import MessageItem from "./message_list_item";

const MessageList = ({messages}) => {
    let messageItems = messages.map(m => {
        return (<MessageItem message={m} key={m}/>)
    });

    return (
        <ul className="list-group">
            {messageItems}
        </ul>
    );
};

export default MessageList;