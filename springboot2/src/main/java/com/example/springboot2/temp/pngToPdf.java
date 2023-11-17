//package FileOption.pptxToPdf;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.util.ObjectUtils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Random;
//import java.util.concurrent.*;
//
//
//public class pngToPdf {
//        static ExecutorService POOL = new ThreadPoolExecutor(10, 30, 0L,TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//
//        public static void main(String[] args) throws InterruptedException {
//            long start = System.currentTimeMillis();
//            System.out.println("开始合成图片并发" + start);
//            CountDownLatch countDownLatch = new CountDownLatch(50);
//            for (int i = 0; i < 50; i++) {
//                MakePicture makePicture = new MakePicture();
//                makePicture.setCountDownLatch(countDownLatch);
//                POOL.execute(makePicture);
//            }
//            countDownLatch.await();
//            long end = System.currentTimeMillis();
//            System.out.println("总耗时" + (end - start));
//        }
//
////        static class MakePicture implements Runnable{
////
////            public void setCountDownLatch(CountDownLatch countDownLatch) {
////                this.countDownLatch = countDownLatch;
////            }
////
////            private CountDownLatch countDownLatch;
////
////            static Random r = new Random();
////
//////            public static String getRandomStringInRange(int min, int max) {
//////                return String.valueOf(r.ints(min, (max + 1)).limit(1).findFirst().getAsInt());
//////            }
////
//////            public void run() {
//////                long start = System.currentTimeMillis();
//////                String[] imageFiles = {"1.png", "2.png", "3.png", "4.png","5.png","6.png"}; // 将要转换的PNG文件名列表
//////                String outputFilename = "outPdf/outPdf" + countDownLatch.getCount() + ".pdf"; // 输出PDF文件名
//////                String randomStringInRange = getRandomStringInRange(5, 10);
//////                ByteArrayOutputStream os = null;
//////                try {
//////                    os = new ByteArrayOutputStream();
//////                    Document document = new Document();
//////                    PdfWriter.getInstance(document, os);
//////                    document.open();
//////                    for (Integer i = 0; i < Integer.valueOf(randomStringInRange); i++) {
//////                        Image image = Image.getInstance(imageFiles[i%5]);
//////                        // 设置图像大小
//////                        float width = PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin();
//////                        float scaleFactor = width / image.getWidth();
//////                        image.scalePercent(scaleFactor * 100f);
//////                        document.add(image);
//////                    }
//////                    document.close();
//////                    new FileOutputStream(outputFilename).write(os.toByteArray());
//////                } catch (Exception e) {
//////                    e.printStackTrace();
//////                } finally {
//////                    try {
//////                        if (!ObjectUtils.isEmpty(os)) {
//////                            os.close();
//////                        }
//////                    } catch (IOException e) {
//////                        e.printStackTrace();
//////                    }
//////                }
//////                long end = System.currentTimeMillis();
//////                System.out.println(Thread.currentThread() + "制图耗时---------"  + (end - start) + "制图数量--------" + randomStringInRange);
//////                countDownLatch.countDown();
//////            }
////        }
//
//}
