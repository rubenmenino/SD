package commonInfrastructures;

/**
 *    Este tipo de dados define uma mem�ria gen�rica de tipo est�tico.
 */

public abstract class MemObject
{
  /**
   *  Tamanho da mem�ria em n�mero de posi��es
   *
   *    @serialField nMax
   */

   protected int nMax = 0;

  /**
   *  �rea de armazenamento
   *
   *    @serialField mem
   */

   protected Object [] mem = null;

  /**
   *  Instancia��o da mem�ria.
   *
   *    @param nElem tamanho da mem�ria (n. de elementos do array de armazenamento)
   */

   protected MemObject (int nElem)
   {
     mem = new Object [nElem];
     nMax = nElem;
   }

  /**
   *  Escrita de um valor (m�todo virtual).
   *
   *    @param val valor a armazenar
   */

   protected abstract void write (Object val);

  /**
   *  Leitura de um valor (m�todo virtual).
   *
   *    @return valor armazenado
   */

   protected abstract Object read ();
}
