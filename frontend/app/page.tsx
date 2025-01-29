import axios from "axios";
import Image from "@/components/Home/Image"

export interface APIResponse {
  id: number,
  width: number,
  height: number,
  url: string,
  download_url: string
}
// "id": "102",
// "author": "Ben Moore",
// "width": 4320,
// "height": 3240,
// "url": "https://unsplash.com/photos/pJILiyPdrXI",
// "download_url": "https://picsum.photos/id/102/4320/3240"
// },

export default async function Home() {
  let pics: APIResponse[] = [];
  try {
    const response = await axios.get("https://picsum.photos/v2/list?page=2&limit=200");
    pics = response.data; // Access the data property where the array is located
  } catch (err) {
    console.error(err);
  }

  console.log(pics)

  return (
    <div className="min-h-svh w-svw gap-8 columns-6 bg-black text-white p-8">
      {pics.map((pic) => {
        return (
          <Image image={pic} key={pic.id} />
        )
      })}
    </div>
  );
}
