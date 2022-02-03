package commonInfrastructures;
/**
 *    Este tipo de dados define uma mem�ria de tipo FIFO derivada a partir de uma mem�ria gen�rica de tipo est�tico.
 */

public class MemFIFO extends MemObject
{
  /**
   *  Ponto de inser��o
   *
   *    @serialField inPnt
   */

   private int inPnt = 0;

  /**
   *  Ponto de retirada
   *
   *    @serialField outPnt
   */

   private int outPnt = 0;

  /**
   *  Sinaliza��o de FIFO vazio
   *
   *    @serialField empty
   */

   private boolean empty = true;

  /**
   *  Instancia��o do FIFO.
   *
   *    @param nElem tamanho do FIFO (n. de elementos do array de armazenamento)
   */

   public MemFIFO (int nElem)
   {
     super (nElem);
   }

  /**
   *  Escrita de um valor.
   *
   *    @param val valor a armazenar
   */

   public void write (Object val)
   {
     if ((inPnt != outPnt) || empty)
        { mem[inPnt] = val;
          inPnt += 1;
          inPnt %= nMax;
          empty = false;
        }
   }

  /**
   *  Leitura de um valor.
   *
   *    @return valor armazenado
   */

   public Object read ()
   {
     Object val = null;

     if ((outPnt != inPnt) || !empty)
        { val = mem[outPnt];
          outPnt += 1;
          outPnt %= nMax;
          empty = (inPnt == outPnt);
        }
     return (val);
   }

  /**
   *  Detec��o de FIFO vazio.
   *
   *    @return <li> true, se o FIFO estiver vazio
   *            <li> false, em caso contr�rio
   */

   public boolean empty ()
   {
     return (this.empty);
   }

  /**
   *  Detec��o de FIFO cheio.
   *
   *    @return <li> true, se o FIFO estiver cheio
   *            <li> false, em caso contr�rio
   */

   public boolean full ()
   {
     return (!this.empty && (outPnt == inPnt));
   }
}