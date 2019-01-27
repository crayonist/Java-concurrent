package com.labi.thread.communication;

/**
 * ��ǰ�̵߳��ù��������wait()��ֻ���ͷŵ�ǰ��������ϵ����������ǰ�̻߳������������������������Щ���ǲ��ᱻ�ͷŵġ�
 * ���������ӣ�
 *    �̣߳������û���ͷŻ�ȡ�󵽵�ResourceB�ϵ����������̣߳³��Ի�ȡresourceB�ϵ����ᱻ������
 */
public class ShareVariableWait {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                // ��ȡresourceA��Դ�ļ�������
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    // ��ȡresourceB������Դ�ļ�������
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");

                        // �߳�A���������ͷŻ�ȡ����resourceA����
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                // ��ȡresourceA��Դ�ļ�������
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try to get resourceB lock...");
                    // ��ȡresourceB������Դ�ļ�������
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");

                        // �߳�B���������ͷŻ�ȡ����resourceA����
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // �����߳�
        threadA.start();
        threadB.start();

        // �ȴ������߳̽���
        threadA.join();
        threadB.join();

        System.out.println("main over");

    }

}
