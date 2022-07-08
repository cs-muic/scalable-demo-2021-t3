use hello_world::greeter_client::GreeterClient;
use hello_world::BlessingRequest;
use hello_world::HelloRequest;

pub mod hello_world {
    tonic::include_proto!("helloworld");
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    const DEST_HOST: &'static str = "http://[::1]:50051";
    let mut client = GreeterClient::connect(DEST_HOST).await?;

    let request = tonic::Request::new(HelloRequest {
        name: "Tonic".into(),
    });

    let response = client.say_hello(request).await?;

    println!("RESPONSE={:?}", response);
    let message = response.get_ref();
    println!("The message is {:?}\n", message.message);

    println!("=========== another request =========");
    let request = tonic::Request::new(
        BlessingRequest {
            name: "Scalable People".into(),
            donation_amount: 10000i32,
        }
    );
    let response = client.ask_for_blessing(request).await?;
    println!("Message: {:?}", response.get_ref().message);
    Ok(())
}
