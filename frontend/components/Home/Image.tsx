'use client'
import NextImage from "next/image"

import { APIResponse } from "@/app/page"
import { useState } from "react";

export default function Image({ image }: Readonly<{ image: APIResponse }>) {
  const [loading, setLoading] = useState(true);

  return (
    <div className={`bg-zinc-800 rounded-xl w-full h-${image.height} mb-8 overflow-hidden ${loading ? "animate-pulse" : ""}`}>
      <a href={image.url}>
        <NextImage
          placeholder="empty"
          src={image.download_url}
          width={image.width}
          height={image.height}
          className={`transition-opacity duration-700 ${loading ? "opacity-0" : "opacity-100"}`}
          onLoad={() => setLoading(false)}
          alt="" />
      </a>
    </div>
  )
}